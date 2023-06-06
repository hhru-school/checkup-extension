package ru.hh.school.checkupextension.core.data.utils;

import java.util.List;

public record PaginationResult<T>(int currentPage, int pageSize, int totalPages, long totalRecords, List<T> records) {
}
