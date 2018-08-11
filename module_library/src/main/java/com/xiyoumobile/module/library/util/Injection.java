package com.xiyoumobile.module.library.util;

import com.xiyoumobile.module.library.data.source.Repository;
import com.xiyoumobile.module.library.data.source.local.LocalDataSource;
import com.xiyoumobile.module.library.data.source.remote.RemoteDataSource;
import com.xiyoumoblie.lib.common.utils.Utils;

public class Injection {
    public static Repository provideRepository() {
        return Repository.getInstance(
                RemoteDataSource.getInstance(),
                LocalDataSource.getInstance(Utils.getContext()));
    }
}
