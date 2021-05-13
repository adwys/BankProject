package org.bank.Exceptions;

public class InvalidTransferAmount extends Exception {
    InvalidTransferAmount(String s){
        super(s);
    }
}
