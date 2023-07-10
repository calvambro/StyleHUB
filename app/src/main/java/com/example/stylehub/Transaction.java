package com.example.stylehub;

public class Transaction {
    private int idTransaction;
    private int idProduct;
    private int idUser;
    private String transactionDate;

    public Transaction(){

    }

    public Transaction(int idTransaction, int idProduct, int idUser, String transactionDate) {
        this.idTransaction = idTransaction;
        this.idProduct = idProduct;
        this.idUser = idUser;
        this.transactionDate = transactionDate;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
