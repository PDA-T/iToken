package com.pda.itoken.service.upload.fastdfs;

/**
 * @author PDA
 * @Date 2022/10/21 21:06
 * @Description 文件储存服务接口
 * @since version-1.0
 */
public interface StorageService {

	/**
	 * @author PDA
	 * @Date 2022/10/21 21:07
	 * @Description 上传文件
	 * @Param [data, extName]
	 * @return java.lang.String 成功返回id,失败返回null
	 * @since version-1.0
	 */
	public String upload(byte[] data,String extName);

	/**
	 * @author PDA
	 * @Date 2022/10/21 21:07
	 * @Description 删除文件
	 * @Param [fileId]
	 * @return int 成功返回0,失败返回错误代码
	 * @since version-1.0
	 */
	public int delete(String fileId);
}
