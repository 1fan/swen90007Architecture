package org.zhaobi.web.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.zhaobi.web.dao.CategoryDao;
import org.zhaobi.web.entity.Category;
import org.zhaobi.web.entity.Question;
import org.zhaobi.web.service.CateService;

@Service("cateService")
public class CateServiceImpl implements CateService{
	@Autowired  
	@Qualifier("categoryDao")
	private CategoryDao cateDao;
	
	@Override
	public BigInteger countCate() {
		return this.cateDao.countCate();
	}
	
	public List<Category> getCategory(){
		return this.cateDao.getCategory();
	}
	
	public void update(int cid, String name) {
		this.cateDao.update(cid, name);
	}
	
	public List<Category> search(int cid){
		return this.cateDao.search(cid);
	}
	
	public List<Category> showCategory(int page){
		return this.cateDao.showCategory(page);
	}
	
	public List<Category> showSearch(int cid, int page){
		return this.cateDao.showSearch(cid, page);
	}


}
