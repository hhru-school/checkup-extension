package ru.hh.school.checkupextension.core.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PaginationResultDto<T>(int currentPage, int pageSize, int totalPages, long totalRecords, List<T> records) {
  @JsonCreator
  public PaginationResultDto(
      @JsonProperty("page") int currentPage,
      @JsonProperty("size") int pageSize,
      @JsonProperty("pages") int totalPages,
      @JsonProperty("total") long totalRecords,
      @JsonProperty("records") List<T> records
  ) {
    this.currentPage = currentPage;
    this.pageSize = pageSize;
    this.totalPages = totalPages;
    this.totalRecords = totalRecords;
    this.records = records;
  }
}
