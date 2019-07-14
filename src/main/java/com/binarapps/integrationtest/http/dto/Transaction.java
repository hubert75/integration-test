package com.binarapps.integrationtest.http.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import javax.annotation.Nullable;

@AutoValue
public abstract class Transaction {
    public abstract String transactionId();

    public abstract String bookedDate();

    @Nullable
    public abstract String debtorAccountId();

    @Nullable
    public abstract String creditorAccountId();

    public abstract Payment payment();

    public static TypeAdapter<Transaction> typeAdapter(Gson gson) {
        return new AutoValue_Transaction.GsonTypeAdapter(gson);
    }

    public static Transaction.Builder builder() {
        return new AutoValue_Transaction.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Transaction.Builder setTransactionId(String transactionId);

        public abstract Transaction.Builder setBookedDate(String bookedDate);

        public abstract Transaction.Builder setDebtorAccountId(String debtorAccountId);

        public abstract Transaction.Builder setCreditorAccountId(String creditorAccountId);

        public abstract Transaction.Builder setPayment(Payment payment);

        public abstract Transaction build();
    }
}
