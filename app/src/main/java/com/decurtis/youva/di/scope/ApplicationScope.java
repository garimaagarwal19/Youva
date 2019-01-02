package com.decurtis.youva.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Garima Chamaria on 02/01/19.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}
