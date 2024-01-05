package com.ah.backendreadlib.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ReadAbstractRepository<E, ID, R extends JpaRepository<E, ID>> {

	protected R jpaRepository;
	
	/** idで検索しEntityを返す */
	public E findById(ID id) {
		return jpaRepository.findById(id).orElseThrow();
	}
	
	/** idで検索し、Optinal&lt;Entity&gt;で返す */
	public Optional<E> findOptById(ID id) {
		return jpaRepository.findById(id);
	}
	
	/** idのレコードが存在するかチェック */
	public boolean isExistsById(ID id) {
		return this.findOptById(id).isPresent();
	}
	
	/**
	 * idで検索し、存在する時は返す <br>
	 * {@linkplain ReadAbstractRepository#findOptById(Object)} を使用して、存在チェックする想定
	 */
	public abstract E existCheckAndGetById(ID id);

	/**
	 * それぞれのEntityの一意の項目ですでに存在するか確認する
	 * @param reqData リクエストのdata。各クラスにデシリアライズする
	 * @return
	 */
	public abstract boolean isExsitsByUniqueCol(Object reqData);
	
	/**
	 * 特定レコード(id)以外で、それぞれのEntityの一意の項目ですでに存在するか確認する
	 * @param reqData リクエストのdata。各クラスにデシリアライズする
	 * @param id PK
	 * @return
	 */
	public abstract boolean isExsitsByUniqueColNotEqId(Object reqData, ID id);
}
