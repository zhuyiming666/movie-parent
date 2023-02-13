package com.itheima.common;

import com.aliyun.vod.upload.UploadVideo;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

//视频操作
@Component
public class VodTemplate {

    @Autowired
    private VodConfig vodConfig;

    //初始化
    public DefaultAcsClient initVodClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                vodConfig.getRegionId(),
                vodConfig.getAccessKeyId(),
                vodConfig.getAccessKeySecret());
        return new DefaultAcsClient(profile);
    }

    //上传视频,返回视频id
    public String uploadVideo(String fileName, InputStream inputStream) {
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(
                vodConfig.getAccessKeyId(),
                vodConfig.getAccessKeySecret(),
                title,
                fileName,
                inputStream);
        //手动设置阿里云存储区域地址
        request.setStorageLocation("outin-8ac4068285c411ed8fa100163e10fc78.oss-cn-shenzhen.aliyuncs.com");
        //手动设置服务接入点
        request.setApiRegionId("cn-shenzhen");
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = response.getVideoId();
        return videoId;
    }

    //获取播放凭证函数
    public GetVideoPlayAuthResponse getVideoPlayAuth(String videoId) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        return initVodClient().getAcsResponse(request);
    }

    //使用流式上传接口
    public String uploadVideo(MultipartFile file) {
        UploadStreamRequest request = null;
        try{
            String fileName = file.getOriginalFilename();
            //title不让它带后缀名，个人建议
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            request = new UploadStreamRequest(vodConfig.getAccessKeyId(), vodConfig.getAccessKeySecret(), title, fileName, inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        //一般我们都需要保存上传视频的ID
        String videoId = null;
        //创建实现上传的对象
        UploadVideo uploader = new UploadVideoImpl();
        //上传动作，到这一步，已经上传成功
        UploadStreamResponse response = uploader.uploadStream(request);
        //下面是打印信息，用于测试，并且保存下videoId，
        //以便后面通过videoId获取到视频的播放地址或播放凭证
        System.out.println("RequestId=" + response.getRequestId());
        if(response.isSuccess()){
            videoId = response.getVideoId();
            System.out.println("VideoId=" + response.getVideoId());
        }else{
            System.out.println("videoId=" + response.getVideoId());
            System.out.println("ErrorCode=" + response.getCode());
            System.out.println("ErrorMessage=" + response.getMessage());
        }
        return videoId;
    }
}
