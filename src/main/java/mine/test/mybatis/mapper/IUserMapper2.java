package mine.test.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import mine.test.mybatis.domain.User;

public interface IUserMapper2 {
    @Select({ "select * from `user` where id = #{id}" })
    User getUserById(int id);
}
