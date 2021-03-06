package com.stalary.book.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Book
 *
 * @author hawk
 * @since 2018/02/09
 */

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book extends BaseEntity {

    /**
     * 书名
     */
    private String bookName;

    /**
     * 上传该书的用户id
     */
    private int userId;

    /**
     * 封面链接
     */
    private String coverUrl;

    /**
     * 文件链接
     */
    private String pdfUrl;

    /**
     * 下载数量
     */
    private int downloadCount;

}