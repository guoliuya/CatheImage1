package com.example.catheimage1.bitmap2;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
* @ClassName: CacheManger
* @Description: TODO SD图片缓存管理工具
* @author Cheng Yong
* @date  2013-4-11
*
*/
public class CacheManger {

	private final static String TAG = "CacheManger";

	// SD卡存数数量
	private final static int SD_CACHE_IMG_SIZE = 5000;
	// 批量删除数量
	private final static int SD_DEL_COUNT = 50;

	/**
	* @Title: delOverflowPic
	* @Description: TODO 删除超出索引的图片 保持 先进先删规则
	* @param @param cacheFiles
	* @param @param file
	* @param @param picSavePath
	* @param @return    参数文件
	* @return List<File>    返回类型
	* @throws
	*/
	public synchronized List<File> delOverflowPic(List<File> cacheFiles, File file,String picSavePath) {
		try {
			if (cacheFiles == null) {
				cacheFiles = getCacheFiles(picSavePath);
			}
			if (cacheFiles != null) {
				// 添加到集合
				cacheFiles.add(file);
				// 如果SD缓存文件数量大于设置的数量
				if (cacheFiles.size() > SD_CACHE_IMG_SIZE) {

					// 超出缓存数量 + 批量删除数量
					int outSize = cacheFiles.size() - SD_CACHE_IMG_SIZE
							+ SD_DEL_COUNT;
					// 获取待删除集合
					List<File> delFiles = cacheFiles.subList(0, outSize);
					// 删除文件
					for (int i = 0; i < delFiles.size(); i++) {
						if (delFiles.get(i).exists()) {
							delFiles.get(i).delete();
						}
					}
					// 删除集合元素
					cacheFiles = cacheFiles.subList(outSize, cacheFiles.size());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 重新初始化文件列表
			cacheFiles = getCacheFiles(picSavePath);
		}
		
		return cacheFiles;
	}
	
	/**
	* @Title: getCacheFiles
	* @Description: TODO 获取缓存图片集合
	* @param @param picSavePath
	* @param @return    参数文件
	* @return List<File>    返回类型
	* @throws
	*/
	public List<File> getCacheFiles(String picSavePath) {
		try {
			List<File> filesList = null;
			File file = new File(picSavePath);
			// 判断文件目录是否存在
			if (!file.exists()) {
				file.mkdir();
			}
			File[] files = file.listFiles();
			// 如果SD缓存文件数量大于设置的数量
			if (null != files && files.length > 0) {
				filesList = new ArrayList<File>();
				Collections.addAll(filesList, files);
				SortList<File> sortList = new SortList<File>();
				// 最后修改时间正序排列
				sortList.Sort(filesList, "lastModified", null);
			}
			return filesList;
		} catch (Exception e) {
			return null;
		}
	}

}
