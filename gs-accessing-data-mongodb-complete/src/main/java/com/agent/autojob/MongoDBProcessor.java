package com.agent.autojob;
import org.springframework.batch.item.ItemProcessor;

import com.agent.autojob.MongoDBEntity;

public class MongoDBProcessor implements
		ItemProcessor<MongoDBEntity, MongoDBEntity> {

	@Override
	public MongoDBEntity process(MongoDBEntity entity) throws Exception {
		return entity;
	}

}