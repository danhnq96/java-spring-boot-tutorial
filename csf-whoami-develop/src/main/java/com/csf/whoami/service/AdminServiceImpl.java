package com.csf.whoami.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csf.whoami.converter.AdminDomain;
import com.csf.whoami.converter.ConvertAdminDTO;
import com.csf.whoami.database.TbAdmin;
import com.csf.whoami.dto.AdminInfo;
import com.csf.whoami.dto.SearchVO;
import com.csf.whoami.repository.AdminRepository;

//@Log4j2
@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {

    @Value("${databasekey}")
    private String databasekey;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public AdminDomain findByName(String name) {
//		return adminRepo.findByName(name);
        return null;
    }

    @Override
    public AdminDomain findByNameAndPassword(String name, String password) {
        TbAdmin admin = adminRepository.findByNameAndPassword(name, password, databasekey);
        return ConvertAdminDTO.dbToDomain(admin);
    }

    @Override
    public Page<AdminInfo> adminList(SearchVO search, Pageable pageable) {
//        ObjectUtil.removeEmptyField(search);
//        return adminRepository.adminList(search,pageable);
        return null;
    }

    @Override
    public AdminInfo adminDetail(Long id) {
//		return adminRepo.adminDetail(id);
        return null;
    }

    @Override
    public AdminDomain adminFindById(Long id) {
//		Optional<TbAdmin> info = adminRepo.findById(id);
//		return info.get();
        return null;
    }

    @Override
    @Transactional
    public AdminDomain adminUpdate(AdminDomain tbAdmin, AdminInfo info) {
//		adminRepo.adminUpdate(tbAdmin);
//
//		TbAdminRole tbAdminRole = service.findByTbAdminId(info.getId());
//		tbAdminRole.setTbRoleId(info.getRoleId());
//		adminRoleRepo.save(tbAdminRole);
//
//		return tbAdmin;
        return null;
    }

    @Override
    public void tokenUpdate(AdminDomain tbAdmin) {
//		adminRepo.tokenUpdate(tbAdmin);
    }

    @Override
    public AdminDomain findByToken(String token) {
//		return adminRepo.findByToken(token);
        return null;
    }

    @Override
    @Transactional
    public int adminInsert(AdminInfo info) {
//
//		TbAdmin adminCnt = adminRepo.findByName(info.getName());
//
//		if (adminCnt == null) {
//			if (!info.getPassword().equals(info.getPasswordConfirm())) {
//				return 1;
//			} else {
//				TbAdmin admin = new TbAdmin();
//				admin.setName(info.getName());
//				admin.setPassword(info.getPassword());
//				admin.setUsername(info.getUsername());
//				admin.setPhone(info.getPhone());
//				admin.setEmail(info.getEmail());
//
//				adminRepo.adminCreate(admin, databasekey);
//
//				Long adminId = adminRepo.getAdminIdByName(info.getName());
//
//				TbAdminRole tbAdminRole = new TbAdminRole();
//				tbAdminRole.setTbAdminId(adminId);
//				tbAdminRole.setTbRoleId(info.getRoleId());
//
//				adminRoleRepo.save(tbAdminRole);
//
//				return 0;
//			}
//		} else {
//			return 2;
//		}
        return 0;
    }

    @Override
    @Transactional
    public int adminModify(AdminInfo info) throws Exception {

//		if (!info.getPassword().equals(info.getPasswordConfirm())) {
//			return 1;
//		}
//
//		TbAdmin admin = new TbAdmin();
//
//		admin.setId(info.getId());
//		admin.setName(info.getName());
//		admin.setEmail(info.getEmail());
//		admin.setUsername((info.getUsername()));
//		admin.setPassword(info.getPassword());
//
////        adminRepository.save(admin);
//		adminRepo.updateAdmin(admin, databasekey);
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

//		TbAdmin admin = adminRepo.findByName(name);
//		if (admin == null) {
//			throw new UsernameNotFoundException("TbAdmin not found with name: " + name);
//		}
//
//		TbAdminRole role = adminRoleRepo.findByTbAdminId(admin.getId());
//		String roleName = null;
//		if (role.getTbRoleId() == 1) {
//			roleName = "ROLE_ADMIN";
//		} else {
//			roleName = "ROLE_USER";
//		}
//
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		authorities.add(new SimpleGrantedAuthority(roleName));
//
//		return new User(admin.getName(), admin.getPassword(), authorities);
        return null;
    }

    @Override
    public void resetPassword(Long id) throws UsernameNotFoundException {
//		adminRepo.resetPassword(id, databasekey);
    }

    @Override
    public String getAdminPassword(Long id, String databasekey) {
//		return adminRepo.getAdminPassword(id, databasekey);
        return null;
    }
}
