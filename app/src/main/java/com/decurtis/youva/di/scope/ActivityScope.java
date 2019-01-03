package com.decurtis.youva.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Scope;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@Qualifier
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
