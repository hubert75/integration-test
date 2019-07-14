package com.binarapps.integrationtest.http.dto;

import com.google.auto.value.AutoValue;

import javax.annotation.Nullable;

@AutoValue
public abstract class TransactionListParams {

    public static TransactionListParams.Builder builder() {
        return new AutoValue_TransactionListParams.Builder();
    }

    public abstract Integer firstIndex();

    public abstract Integer lastIndex();

    @Nullable
    public abstract String pageIndex();

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract TransactionListParams.Builder setPageIndex(String pageIndex);

        public abstract TransactionListParams.Builder setLastIndex(Integer lastIndex);

        public abstract TransactionListParams.Builder setFirstIndex(Integer firstIndex);

        public abstract TransactionListParams build();
    }

}
