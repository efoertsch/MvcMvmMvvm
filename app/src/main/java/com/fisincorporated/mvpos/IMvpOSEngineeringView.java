package com.fisincorporated.mvpos;

import rx.subjects.PublishSubject;

public interface IMvpOSEngineeringView {

    PublishSubject getSwitchChangePublishSubject();

    PublishSubject getLogUpdatePublishSubject();

}
