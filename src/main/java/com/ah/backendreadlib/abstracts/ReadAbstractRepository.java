package com.ah.backendreadlib.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class ReadAbstractRepository<E, ID, R extends JpaRepository<E, ID>, T> {

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
	public abstract boolean isExistsByUniqueCol(T reqData);
	
	/**
	 * 特定レコード(id)以外で、それぞれのEntityの一意の項目ですでに存在するか確認する
	 * @param reqData リクエストのdata。各クラスにデシリアライズする
	 * @param id PK
	 * @return
	 */
	public abstract boolean isExistsByUniqueColNotEqId(T reqData, ID id);
	
	/**
	 * commit前のentityで、それぞれの一意の項目ですでに存在するか確認する。<br>
	 * 部分更新等でのT reqBeanが使えない時用<br>
	 * req.save()前にこのselectが走ると、更新中のトランザクションがコミットされるので、新規トランザクションを貼る
	 * @param entity
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public abstract boolean isExistsByUniqueColNotEqIdForEntity(E entity);
}
