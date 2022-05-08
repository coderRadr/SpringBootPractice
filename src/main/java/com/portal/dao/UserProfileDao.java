package com.portal.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;

import com.portal.models.ProfileModel;

/**
 * Dao Class to retrieve User profile Details using MySql Stored Procedure
 */
@Repository
public class UserProfileDao {

	public static final String UNIQUE_ID = "unique_id";
	private static final String GET_UNIQUE_ID = "{call employee.get_id(?, ?)}";
	private static final String USER_ID = "user_id";
	private static final String PWD = "pwd";
	private static final String GET_PROFILE_DTLS = "{call employee.get_user_profile(?)}";
	private static final String F_NAME = "f_name";
	private static final String L_NAME = "l_name";
	private static final String EMAIL = "email";
	private static final String EXP_YEARS = "exp";
	private static final String AREA_OF_EXPERTISE = "area_expertise";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String getUniqueId(String userId, String password) {
		try {
			String uniqueId = null;
			CallableStatement st = jdbcTemplate.getDataSource().getConnection().prepareCall(GET_UNIQUE_ID);
			st.setString(USER_ID, userId);
			st.setString(PWD, password);
			ResultSet resultSet = st.executeQuery();
			if(!resultSet.isBeforeFirst() && resultSet.getRow() == 0) {
				String msg = String.format("No records found with given id: '%s' (Given Password/userId combination doesn't exist)", userId);
				throw new RestClientException(msg);
			}
			while (resultSet.next()) {
				uniqueId = resultSet.getString(UNIQUE_ID);
			}
			return uniqueId;
		} catch (SQLException e) {
			throw new RestClientException(e.getMessage());
		}

	}

	public List<ProfileModel> getProfileDetails(String uniqueId) {
		try {
			List<ProfileModel> allEmployees = new ArrayList<>();
			CallableStatement st = jdbcTemplate.getDataSource().getConnection().prepareCall(GET_PROFILE_DTLS);
			if(StringUtils.isNotEmpty(uniqueId)) {
				st.setString(UNIQUE_ID, uniqueId);
			} else {
				st.setNull(UNIQUE_ID, Types.VARCHAR);
			}
			ResultSet resultSet = st.executeQuery();
			while (resultSet.next()) {
				ProfileModel model = new ProfileModel();
				model.setFirstName(resultSet.getString(F_NAME));
				model.setLastName(resultSet.getString(L_NAME));
				model.setEmail(resultSet.getString(EMAIL));
				model.setYearsOfExperience(resultSet.getInt(EXP_YEARS));
				String allAreas = resultSet.getString(AREA_OF_EXPERTISE);
				model.setAreaOfExpertise(Arrays.stream(StringUtils.split(allAreas, ',')).map(src -> src.strip())
						.collect(Collectors.toList()));
				allEmployees.add(model);
			}
			return allEmployees;
		} catch (SQLException e) {
			throw new RestClientException(e.getMessage());
		}
	}
}
