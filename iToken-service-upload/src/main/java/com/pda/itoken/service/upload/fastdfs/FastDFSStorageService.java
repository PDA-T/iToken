package com.pda.itoken.service.upload.fastdfs;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author PDA
 * @Date 2022/10/21 21:09
 * @Description 文件储存服务实现
 * @since version-1.0
 */
public class FastDFSStorageService implements StorageService, InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(FastDFSStorageService.class);

	private TrackerClient trackerClient;

	@Value("${storage.fastdfs.tracker_server}")
	private String trackerServer;

	/**
	 * @author PDA
	 * @Date 2022/10/21 21:30
	 * @Description 上传
	 * @Param [data, extName]
	 * @return java.lang.String
	 * @since version-1.0
	 */
	@Override
	public String upload(byte[] data, String extName) {
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;
		StorageClient1 storageClient1 = null;
		try {
			NameValuePair[] meta_list = null; // new NameValuePair[0];

			trackerServer = trackerClient.getTrackerServer();
			if (trackerServer == null) {
				logger.error("getConnection return null");
			}
			storageServer = trackerClient.getStoreStorage(trackerServer);
			storageClient1 = new StorageClient1(trackerServer, storageServer);
			String fileid = storageClient1.upload_file1(data, extName, meta_list);
			logger.debug("uploaded file <{}>", fileid);
			return fileid;
		} catch (Exception ex) {
			logger.error("Upload fail", ex);
			return null;
		} finally {
			storageClient1 = null;
		}
	}

	/**
	 * @author PDA
	 * @Date 2022/10/21 21:31
	 * @Description 删除
	 * @Param [fileId]
	 * @return int
	 * @since version-1.0
	 */
	@Override
	public int delete(String fileId) {
//        System.out.println("deleting ....");
		TrackerServer trackerServer = null;
		StorageServer storageServer = null;
		StorageClient1 storageClient1 = null;
		int index = fileId.indexOf('/');
		String groupName = fileId.substring(0, index);
		try {
			trackerServer = trackerClient.getTrackerServer();
			if (trackerServer == null) {
				logger.error("getConnection return null");
			}
			storageServer = trackerClient.getStoreStorage(trackerServer, groupName);
			storageClient1 = new StorageClient1(trackerServer, storageServer);
			int result = storageClient1.delete_file1(fileId);
			return result;
		} catch (Exception ex) {
			logger.error("Delete fail", ex);
			return 1;
		} finally {
			storageClient1 = null;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		File confFile = File.createTempFile("fastdfs", ".conf");
		PrintWriter confWriter = new PrintWriter(new FileWriter(confFile));
		confWriter.println("tracker_server=" + trackerServer);
		confWriter.close();
		ClientGlobal.init(confFile.getAbsolutePath());
		confFile.delete();
		TrackerGroup trackerGroup = ClientGlobal.g_tracker_group;
		trackerClient = new TrackerClient(trackerGroup);

		logger.info("Init FastDFS with tracker_server : {}", trackerServer);
	}
}
