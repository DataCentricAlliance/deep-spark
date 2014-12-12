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

package com.stratio.deep.jdbc.extractor;

import com.stratio.deep.commons.entity.Cells;
import com.stratio.deep.jdbc.reader.JdbcReader;
import com.stratio.deep.jdbc.writer.JdbcWriter;
import org.mockito.internal.util.reflection.Whitebox;
import org.powermock.api.mockito.PowerMockito;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

/**
 * Created by mariomgal on 12/12/14.
 */
@Test(groups = { "UnitTests" })
public class JdbcNativeCellExtractorTest {

    private JdbcReader jdbcReader = PowerMockito.mock(JdbcReader.class);

    private JdbcWriter jdbcWriter = PowerMockito.mock(JdbcWriter.class);

    @Test
    public void testSaveRdd() throws Exception {
        JdbcNativeCellExtractor extractor = createJdbcNativeExtractor();
        extractor.saveRDD(new Cells());
        verify(jdbcWriter, times(1)).save(anyMap());
    }

    private JdbcNativeCellExtractor createJdbcNativeExtractor() {
        JdbcNativeCellExtractor extractor = new JdbcNativeCellExtractor();
        Whitebox.setInternalState(extractor, "jdbcReader", jdbcReader);
        Whitebox.setInternalState(extractor, "jdbcWriter", jdbcWriter);
        return extractor;
    }

}
