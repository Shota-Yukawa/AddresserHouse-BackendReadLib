package com.ah.backendreadlib.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Entity;

public abstract class ReadAbstractRepository <T , R extends JpaRepository<Entity, Object>>{

	protected R jpaRepository;
	
	/**
	 * 各サービスクラスごとにJpaRepositoryの実装クラスをセットさせる	<br>
	 * 実装クラスで＠postConstructを付与すること
	 * @param rep
	 */
	protected abstract void setRepository(R rep);
	
	protected Entity findById(Long id) {
		return jpaRepository.findById(id).orElseThrow();
	}
	
	protected Optional<Entity> findOptById(Long id) {
		return jpaRepository.findById(id);
	}
	
	/**
	 * それぞれのEntityの一意の項目ですでに存在するか確認する
	 * @param id
	 * @return
	 */
	abstract boolean isExsits(Long id);
}
