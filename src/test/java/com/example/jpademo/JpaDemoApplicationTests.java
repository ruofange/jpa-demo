package com.example.jpademo;

import com.example.jpademo.entity.*;
import com.example.jpademo.repository.REquipmentRepository;
import com.example.jpademo.repository.RFieldMapRepository;
import com.example.jpademo.repository.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

@SpringBootTest(classes = JpaDemoApplication.class)
class JpaDemoApplicationTests {

    @Autowired
    private RFieldMapRepository fieldMapRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private REquipmentRepository equipmentRepository;

    @Autowired
    private TagRepository tagRepository;


    private final CountDownLatch latch = new CountDownLatch(1);


    @Test
    @Modifying
    public void makeFakeData() throws Exception {
        List<RFieldMap> fieldList = fieldMapRepository.findAll();
        //要制作的假数据量
        int makeSize = 140000;
        //数据库默认最大连接数150 我们调整到140测试数据库压力
        int parallel = 140;


        ThreadPoolExecutor fixedThreadPool = new ThreadPoolExecutor(parallel, parallel, 40, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        for (int j = 0; j < parallel; j++) {
            fixedThreadPool.submit(new Thread(() -> {
                for (int i = 0; i < makeSize / parallel; i++) {
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
                        entityManager.persist(rEquipment);
                        //补充tag
                        List<Tag> tagList = fieldList.stream()
                                .map(field -> {
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
                        tagList.forEach(tag -> {
                            stringBuilder.append("('").append(tag.getId().getOwnerId()).append("'").append(",");
                            stringBuilder.append("'").append(tag.getId().getTagKey()).append("'").append(",");
                            stringBuilder.append("'").append(tag.getTagValue()).append("')").append(",");
                        });
                        stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "").append(";");
                        entityManager.createNativeQuery(stringBuilder.toString()).executeUpdate();
                        System.out.println(Thread.currentThread().getName().concat("-").concat(String.valueOf(finalI)) + "完成插入");
                     /*   try {
                            Thread.currentThread().wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Thread.currentThread().getName().concat("-").concat(String.valueOf(finalI))+"睡醒了");*/
                        status.flush();
                        return null;
                    });

                }
            }));
        }
        latch.await();

    }

    @Test
    public void makeYjYdData() throws Exception {
        long time = new Date().getTime();
        Tag tag = new Tag();
        TagId tagId = new TagId();
        tagId.setTagKey("ReverseField9");
        tag.setId(tagId);
        List<Tag> all;
        int offset = 0;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(140, 1400,
                30, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

        do {
           // all = tagRepository.findAll(Example.of(tag), PageRequest.of(offset, 14000)).getContent();
            all = entityManager.createNativeQuery("select * from tag where TagKey = 'ReverseField9' " +
                    "limit "+offset+", "+14000+";", Tag.class)
                    .getResultList();
            List<Method> setMethodList = Arrays.stream(Yjyd.class.getDeclaredMethods())
                    .filter(method -> method.getName().toLowerCase().startsWith("set") &&
                            !method.getName().equals("setSbbm()"))
                    .toList();
            List<Yjyd> insertList = all.stream().map(t -> {
                Yjyd yjyd = new Yjyd();
                yjyd.setSbbm(t.getTagValue());
                for (Method method : setMethodList) {
                    try {
                        method.invoke(yjyd, UUID.randomUUID().toString().replace("-", "").toUpperCase());
                    } catch (Exception ignore) {
                    }
                }
                return yjyd;
            }).toList();
            for (int i = 0; i < insertList.size(); i = i + 140) {
                List<Yjyd> yjyds = insertList.subList(i, Math.min(i + 140, insertList.size()));
                transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
                threadPoolExecutor.submit(new Runnable() {
                    @Override
                    public void run() {
                        transactionTemplate.execute(new TransactionCallback<Void>() {
                            @Override
                            public Void doInTransaction(TransactionStatus status) {
                                yjyds.forEach(entityManager::persist);
                                status.flush();
                                return null;
                            }
                        });
                    }
                });
            }
            offset = offset + 14000;
        } while (!all.isEmpty());
        latch.wait();
        System.out.println("耗时=>" + (new Date().getTime() - time));
    }


}
