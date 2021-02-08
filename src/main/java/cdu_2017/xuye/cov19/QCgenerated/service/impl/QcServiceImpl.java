package cdu_2017.xuye.cov19.QCgenerated.service.impl;

import cdu_2017.xuye.cov19.QCgenerated.dao.QcDao;
import cdu_2017.xuye.cov19.QCgenerated.model.Qc;
import cdu_2017.xuye.cov19.QCgenerated.service.QcService;
import cdu_2017.xuye.cov19.register.dao.UserAreaDao;
import cdu_2017.xuye.cov19.register.model.UserArea;
import cdu_2017.xuye.cov19.register.service.UserAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("qcService")
public class QcServiceImpl extends ServiceImpl<QcDao, Qc> implements QcService {
}
