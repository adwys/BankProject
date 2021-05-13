package org.bank.Exceptions;

public class InvalidTransferAmount extends Exception {
    public InvalidTransferAmount(String s){
        super(s);
    }
}
