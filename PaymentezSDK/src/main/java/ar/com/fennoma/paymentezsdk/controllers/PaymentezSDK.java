package ar.com.fennoma.paymentezsdk.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import ar.com.fennoma.paymentezsdk.models.PmzError;
import ar.com.fennoma.paymentezsdk.models.PmzOrder;

public class PaymentezSDK {

    @SuppressLint("StaticFieldLeak")
    private static PaymentezSDK instance;

    public static void initialize(String apiKey, String secret) {
        PaymentezSDK instance = getInstance();
        instance.setApiKey(apiKey);
        instance.setSecret(secret);
    }

    public interface PmzSearchListener {
        void onFinishedSuccessfully(PmzOrder order);
        void onCancel();
    }

    public interface PmzPaymentCheckerListener {
        void onFinishedSuccessfully(PmzOrder order);
        void onError(PmzOrder order, PmzError error);
    }

    public static PaymentezSDK getInstance() {
        if(instance == null) {
            instance = new PaymentezSDK();
        }
        return instance;
    }

    public void startSearch(PmzSearchListener listener) {
        if(isInitialized() && isContextAvailable()) {
            PmzData.getInstance().startSearch(listener);
        }
    }

    private boolean isInitialized() {
        if(TextUtils.isEmpty(PmzData.getInstance().getApiKey()) || TextUtils.isEmpty(PmzData.getInstance().getSecret())) {
            throw new RuntimeException("PaymentezSDK not initialized");
        } else {
            return true;
        }
    }

    private boolean isContextAvailable() {
        if(PmzData.getInstance().getContext() == null) {
            throw new RuntimeException("PaymentezSDK has no context provided");
        } else {
            return true;
        }
    }

    public void startPaymentChecking(PmzOrder order, PmzPaymentCheckerListener listener) {
        if(isInitialized()) {
            PmzData.getInstance().startPaymentChecking(order, listener);
        }
    }

    public PaymentezSDK setButtonBackgroundColor(Integer buttonBackgroundColor) {
        PmzData.getInstance().setButtonBackgroundColor(buttonBackgroundColor);
        return this;
    }

    public PaymentezSDK setButtonTextColor(Integer buttonTextColor) {
        PmzData.getInstance().setButtonTextColor(buttonTextColor);
        return this;
    }

    public PaymentezSDK setTextColor(Integer textColor) {
        PmzData.getInstance().setTextColor(textColor);
        return this;
    }

    public PaymentezSDK setBackgroundColor(int color) {
        PmzData.getInstance().setBackgroundColor(color);
        return this;
    }

    public PaymentezSDK setContext(Context context) {
        PmzData.getInstance().setContext(context);
        return this;
    }

    public void setOrderResult(PmzOrder order) {
        PmzData.getInstance().setOrderResult(order);
    }

    public void setSecret(String secret) {
        PmzData.getInstance().setSecret(secret);
    }

    public void setApiKey(String apiKey) {
        PmzData.getInstance().setApiKey(apiKey);
    }
}