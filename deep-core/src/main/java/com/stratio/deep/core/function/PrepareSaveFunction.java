/*
 * Copyright 2014, Stratio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stratio.deep.core.function;

import static com.stratio.deep.commons.utils.Utils.getExtractorInstance;
import static com.stratio.deep.core.util.ExtractorClientUtil.getExtractorClient;

import java.io.Serializable;

import scala.collection.Iterator;
import scala.runtime.AbstractFunction1;
import scala.runtime.BoxedUnit;

import com.stratio.deep.commons.config.BaseConfig;
import com.stratio.deep.commons.exception.DeepExtractorinitializationException;
import com.stratio.deep.commons.functions.SaveFunction;
import com.stratio.deep.commons.rdd.IExtractor;

/**
 * Created by rcrespo on 28/08/14.
 */
public class PrepareSaveFunction<T, S extends BaseConfig<T>> extends AbstractFunction1<Iterator<T>,
        BoxedUnit> implements Serializable {

    private final S config;

    private final T first;

    private final SaveFunction function;

    public PrepareSaveFunction(SaveFunction function, S config, T first) {
        this.first = first;
        this.config = config;
        this.function = function;
    }

    @Override
    public BoxedUnit apply(Iterator<T> v1) {
        IExtractor<T, S> extractor;
        try {
            extractor = getExtractorInstance(config);
        } catch (DeepExtractorinitializationException e) {
            extractor = getExtractorClient();
        }

        if (function == null) {
            extractor.initSave(config, first,null);
        }else{
            extractor.initSave(config,first, function);
        }

        while (v1.hasNext()) {
            extractor.saveRDD(v1.next(), function);
        }
        config.setPartitionId(config.getPartitionId() + 1);
        extractor.close();
        return null;
    }

}
