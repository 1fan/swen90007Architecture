package org.zhaobi.web.dao;

import java.math.BigInteger;
import java.util.List;

import org.zhaobi.web.entity.Category;
import org.zhaobi.web.entity.Question;
import org.zhaobi.web.entity.Users;

public interface CategoryDao {
	public BigInteger countCate();
	public List<Category> getCategory();
	public void update(int cid, String name);
	public List<Category> search(int cid);
	public List<Category> showCategory(int page);
	public List<Category> showSearch(int cid, int page);
}
