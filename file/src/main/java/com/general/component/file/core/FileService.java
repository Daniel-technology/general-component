package com.general.component.file.core;

import com.general.component.file.config.FileProperty;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务
 *
 * @author littlesnail
 * @create 2023−03-11 8:56 PM
 */
public abstract class FileService {

    /**
     * 文件配置
     */
    protected final FileProperty fileProperty;

    public FileService(FileProperty fileProperty) {
        this.fileProperty = fileProperty;
    }

    public final FileProperty getConfiguration() {
        return this.fileProperty;
    }


    /**
     * 上传文件
     *
     * @param file file
     * @return
     * @throws Exception
     */
    public abstract String upload(MultipartFile file) throws Exception;
//    /**
//     * 获取文件信息，使用默认的根路径
//     *
//     * @param relativePath 相对于根路径的文件路径
//     * @return 文件基本描述信息，如名称，长度
//     */
//    public abstract Optional<FileConfig> getFile(String relativePath);
//
//    /**
//     * 将字节数组内容写入对象存储中，适用于小文件。
//     *
//     * @param relativePath 相对路径
//     * @param data
//     * @return 文件基本描述信息，如名称，长度
//     */
//    public FileInfo writeFile(String relativePath, byte[] data) {
//        return writeFile(null, relativePath, data);
//    }
//
//    /**
//     * 将字节数组内容写入对象存储中，适用于小文件。
//     *
//     * @param basePath     指定根路径或桶名称
//     * @param relativePath 相对路径
//     * @param data         文件字节数组
//     * @return 文件基本描述信息，如名称，长度
//     */
//    public abstract FileInfo writeFile(String basePath, String relativePath, byte[] data);
//
//    /**
//     * 将输入流写入对象存储中，路径是配置的根路径+相对地址。调用者负责关闭输入流防止内存泄露
//     *
//     * @param relativePath 相对路径
//     * @param in           输入流
//     * @return file 文件基本描述信息，如名称，长度
//     */
//    public FileInfo writeFile(String relativePath, InputStream in) {
//        return writeFile(null, relativePath, in);
//    }
//
//    /**
//     * 将输入流写入对象存储中，路径是指定根路径+相对地址。调用者负责关闭输入流防止内存泄露
//     *
//     * @param basePath     指定根路径或桶名称
//     * @param relativePath 相对路径
//     * @param in           输入流
//     * @return 文件基本描述信息，如名称，长度
//     */
//    public abstract FileInfo writeFile(String basePath, String relativePath, InputStream in);
//
//    /**
//     * 将本地文件写入对象存储中，路径是配置的根路径+相对地址
//     *
//     * @param relativePath 相对地址
//     * @param srcFile      本地文件对象
//     * @return 文件基本描述信息，如名称，长度
//     */
//    public FileInfo writeFile(String relativePath, File srcFile) {
//        return writeFile(null, relativePath, srcFile);
//    }
//
//    /**
//     * 将本地文件写入对象存储中
//     *
//     * @param basePath     指定根路径或桶名称
//     * @param relativePath 相对地址
//     * @param srcFile      本地文件对象
//     * @return 文件基本描述信息，如名称，长度
//     */
//    public abstract FileInfo writeFile(String basePath, String relativePath, File srcFile);
//
//    /**
//     * 根据文件的绝对地址读取文件写入到StringBuilder中。注意用完需要释放StringBuilder防止内存泄露。
//     * 此方法适用于小文件读取
//     *
//     * @param absolutePath 文件绝对路径
//     * @param buf          文件内容写入ByteBuffer
//     * @return 文件长度
//     */
//    public abstract long readFile(String absolutePath, StringBuilder buf);
//
//    /**
//     * 根据文件的绝对地址读取文件写入到ByteBuffer中。如果文件尺寸大于缓冲大小，需要扩容ByteBuffer。注意用完需要释放ByteBuff防止内存泄露。
//     * 此方法适用于小文件读取
//     *
//     * @param absolutePath 文件绝对路径
//     * @param buf          文件内容写入ByteBuffer
//     * @return 文件长度
//     */
//    public abstract long readFile(String absolutePath, ByteBuffer buf);
//
//    /**
//     * 根据文件的绝对地址读取文件到输出流中，调用者需要负责关闭输出流
//     *
//     * @param 文件绝对路径
//     * @param 输出流
//     * @return 文件长度
//     */
//    public abstract long readFile(String absolutePath, OutputStream out);
//
//    /**
//     * 读取文件指定区域数据块
//     *
//     * @param absolutePath 文件对象路径
//     * @param out          数据输出流
//     * @param offset       文件数据块起点
//     * @param len          文件数据块长度 <= 0 的值表示到文件末尾
//     * @return 读取的数据长度
//     */
//    public abstract long readFile(String absolutePath, OutputStream out, long offset, long len);
//
//    /**
//     * 删除一组文件
//     *
//     * @param absolutePaths 文件绝对路径列表
//     * @return 是否删除成功
//     */
//    public abstract boolean deleteFile(List<String> absolutePaths);
}
