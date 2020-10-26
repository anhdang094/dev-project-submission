package dev.remitano.infrastructure.dto;

import java.util.List;

/**
 * @author anhdx
 */
public class PaginationCustom<T> {

    private List<T> contents;
    private long size;
    private long pageNumber;
    private long totalPage;

    public List<T> getContents() {
        return contents;
    }

    public void setContents(List<T> contents) {
        this.contents = contents;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

}
