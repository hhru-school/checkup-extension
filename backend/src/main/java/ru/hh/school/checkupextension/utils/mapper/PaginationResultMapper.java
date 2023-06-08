package ru.hh.school.checkupextension.utils.mapper;

import java.util.function.Function;
import ru.hh.school.checkupextension.core.data.dto.PaginationResultDto;
import ru.hh.school.checkupextension.core.data.utils.PaginationResult;

public class PaginationResultMapper {
  public static <TInput, TOutput> PaginationResultDto<TOutput> toPaginationResultDto(
      PaginationResult<TInput> result, Function<TInput, TOutput> mapper
  ) {
    var records = result.records().stream().map(mapper).toList();
    return new PaginationResultDto<>(
        result.currentPage(),
        result.pageSize(),
        result.totalPages(),
        result.totalRecords(),
        records
    );
  }
}
