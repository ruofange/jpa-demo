package com.example.jpademo;

import com.example.jpademo.entity.REquipment;
import com.example.jpademo.entity.RFieldMap;
import com.example.jpademo.entity.Tag;
import com.example.jpademo.entity.TagId;
import com.example.jpademo.repository.REquipmentRepository;
import com.example.jpademo.repository.RFieldMapRepository;
import com.example.jpademo.repository.TagRepository;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@SpringBootTest(classes = JpaDemoApplication.class)
class JpaDemoApplicationTests {

    @Autowired
    private RFieldMapRepository fieldMapRepository;

    @Autowired
    private REquipmentRepository equipmentRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    private final JsonMapper mapper = JsonMapper.builder().build();

    private final CountDownLatch latch = new CountDownLatch(1);


    @Test
    @Modifying
    public void makeFakeData() throws Exception {
        List<RFieldMap> fieldList = fieldMapRepository.findAll();
        //要制作的假数据量
        int makeSize = 140000;
        //数据库默认最大连接数150 我们调整到140测试数据库压力
        int parallel = 140;

        ThreadPoolExecutor fixedThreadPool = new ThreadPoolExecutor(parallel,parallel,40, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        for (int j = 0; j < parallel; j++) {
            fixedThreadPool.submit(new Thread(() -> {
                for (int i = 0; i < makeSize/parallel; i++) {
                    int finalI = i;
                    transactionTemplate.execute((TransactionCallback<Void>) status -> {
                        REquipment rEquipment = new REquipment();
                        rEquipment.setEquipmentID(UUID.randomUUID().toString().replace("-", "").toUpperCase());
                        rEquipment.setCode(Thread.currentThread().getName().concat("-").concat(String.valueOf(finalI)));
                        rEquipment.setAreaID(UUID.randomUUID().toString().replace("-", "").toUpperCase());
                        rEquipment.setName(Thread.currentThread().getName().concat("-").concat(String.valueOf(finalI)));
                        rEquipment.setSpell("Spell");
                        rEquipment.setMainType("摄像机");
                        rEquipment.setSubType("固定枪机");
                        rEquipment.setBrand("大华");
                        rEquipment.setModel("Model");
                        rEquipment.setIPAddress("127.0.0.1");
                        rEquipment.setPort("80");
                        rEquipment.setLoginName("admin");
                        rEquipment.setPassword("admin");
                        rEquipment.setIsYJYD("是");
                        rEquipment.setRemark("remark");
                        rEquipment.setUpdateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        rEquipment.setAuditStatus(1);
                        equipmentRepository.save(rEquipment);
                        //补充tag
                        List<Tag> tagList = fieldList.stream()
                                .map(field->{
                                    Tag tag = new Tag();
                                    TagId tagId = new TagId();
                                    tagId.setOwnerId(rEquipment.getEquipmentID());
                                    tagId.setTagKey(field.getFieldName());
                                    tag.setId(tagId);
                                    tag.setTagValue(UUID.randomUUID().toString().replace("-", "").toUpperCase());
                                    return tag;
                                }).toList();
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("insert into tag values ");
                        tagList.forEach(tag->{
                            stringBuilder.append("('").append(tag.getId().getOwnerId()).append("'").append(",");
                            stringBuilder.append("'").append(tag.getId().getTagKey()).append("'").append(",");
                            stringBuilder.append("'").append(tag.getTagValue()).append("')").append(",");
                        });
                        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "").append(";");
                        entityManager.createNativeQuery(stringBuilder.toString()).executeUpdate();
                        System.out.println(Thread.currentThread().getName().concat("-").concat(String.valueOf(finalI))+"完成插入");
                        try {
                            Thread.currentThread().wait(1000*30);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Thread.currentThread().getName().concat("-").concat(String.valueOf(finalI))+"睡醒了");
                        status.flush();
                        return null;
                    });

                }
            }));
        }
        latch.await();

    }


}
