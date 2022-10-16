package com.pda.itoken.common.web.components.datatables;

import com.pda.itoken.common.dto.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author PDA
 * @Date 2022/10/16 18:39
 * @Description Datatables 结果集
 * @since version-1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DataTablesResult extends BaseResult implements Serializable {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private String error;
}
