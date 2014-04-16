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

package com.stratio.deep.cql;

import java.io.Serializable;
import java.util.Arrays;

/**
* Created by luca on 14/04/14.
*/
public class DeepTokenRange implements Comparable<DeepTokenRange>, Serializable {
    private Comparable startToken;
    private Comparable endToken;
    private String[] replicas;

    public DeepTokenRange(Comparable startToken, Comparable endToken) {
        this.startToken = startToken;
        this.endToken = endToken;
    }

    public DeepTokenRange(Comparable startToken, Comparable endToken, String[] replicas) {
        this.startToken = startToken;
        this.endToken = endToken;
        this.replicas = replicas;
    }

    @Override
    public String toString() {
        return "DeepTokenRange{" +
                "startToken=" + startToken +
                ", endToken=" + endToken +
                ", replicas=" + Arrays.toString(replicas) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeepTokenRange that = (DeepTokenRange) o;

        if (!endToken.equals(that.endToken)) return false;
        if (!startToken.equals(that.startToken)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startToken.hashCode();
        result = 31 * result + endToken.hashCode();
        return result;
    }

    public Comparable getStartToken() {
        return startToken;
    }

    public void setStartToken(Comparable startToken) {
        this.startToken = startToken;
    }

    public Comparable getEndToken() {
        return endToken;
    }

    public void setEndToken(Comparable endToken) {
        this.endToken = endToken;
    }

    public String[] getReplicas() {
        return replicas;
    }

    public void setReplicas(String[] replicas) {
        this.replicas = replicas;
    }

    @Override
    public int compareTo(DeepTokenRange o) {
        return startToken.compareTo(o.startToken);
    }
}
