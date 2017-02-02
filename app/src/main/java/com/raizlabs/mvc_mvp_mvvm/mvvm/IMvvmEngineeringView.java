package com.raizlabs.mvc_mvp_mvvm.mvvm;

import rx.subjects.PublishSubject;

public interface IMvvmEngineeringView {

    PublishSubject getSwitchChangePublishSubject();

    PublishSubject getLogUpdatePublishSubject();

}
