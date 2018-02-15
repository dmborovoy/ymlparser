package com.dimas.ymlparser;

import com.dimas.ymlparser.dao.ModelRepository;
import com.dimas.ymlparser.dao.VariationRepository;
import com.dimas.ymlparser.model.Model;
import com.dimas.ymlparser.model.Variation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = YmlparserApplication.class)
public class SpringBootJPAIntegrationTest {

    @Autowired
    private VariationRepository repository;

    @Autowired
    private ModelRepository repository2;

    @Test
    public void ok() {
        Variation variation = new Variation();
        variation.setVariationId("123");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "val1");
        map.put("key2", "val2");
        variation.setParams(map);
        Variation variation1 = repository.save(variation);
        Variation variation2 = repository.findOne("123");
        assertNotNull(variation1);
        assertNotNull(variation2);
        log.info("____{}", variation2.getParams());
        assertNotNull(variation2.getParams());
    }

    @Test
    public void ok2() {
        Model model = new Model("ie", "pic", "descr");
        Model model1 = repository2.save(model);
        Model model2 = repository2.findOne("ie");
        assertNotNull(model1);
        assertNotNull(model2);
        log.info("{}", model2.getVariations());
    }
}