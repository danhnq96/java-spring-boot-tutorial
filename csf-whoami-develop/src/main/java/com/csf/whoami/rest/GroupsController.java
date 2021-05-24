/**
 * Functions list:
 * <p>
 * - Add new group		-- OK
 * - Update group info	-- OK
 * - View user's group	-- OK	Include anonimous.
 * - View all group by username (Admin)	-- OK
 * <p>
 * - Invite user to group.
 * - Accept invite to group.
 * <p>
 * - Kick user out group.
 * - Out group.
 */
package com.csf.whoami.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.csf.whoami.backend.entity.UserInfo;
import com.csf.whoami.backend.service.GroupService;
import com.csf.whoami.common.domain.GroupDomain;
import com.csf.whoami.common.domain.GroupInfoDomain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/groups")
@Api
public class GroupsController {

    @Autowired
    private GroupService groupsService;

    @ApiOperation(value = "Phương thức tìm kiếm Group trong hệ thống.")
    @PostMapping(value = "/find-group", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupDomain> findGroup(@RequestBody GroupDomain domain, @ApiParam(hidden = true) Authentication auth) {
        String userId = null;
        boolean isAuthenticate = false;

        if (auth != null) {
            UserInfo userdto = (UserInfo) auth.getPrincipal();
            userId = userdto.getUserId();
            isAuthenticate = true;
        }

        List<GroupDomain> groups = groupsService.getGroupsByGroupName(domain.getGroupName(), domain.getGroupType(),
                isAuthenticate, userId);
        return groups;
    }

    @ApiOperation(value = "Phương thức tạo mới group.")
    @PostMapping("/add-group")
    @Secured({"ROLE_USER"})
    public GroupDomain addNewGroup(@RequestBody GroupDomain domain, @ApiParam(hidden = true) Authentication auth)
            throws Exception {

        UserInfo userdto = (UserInfo) auth.getPrincipal();
        String userId = userdto.getUserId();

        GroupDomain group = groupsService.addNewGroup(domain, userId);
//		if (group == null) {
//			response.setSuccess(false);
//			response.setMessage("Can not create new group.");
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//		}
        return group;
    }

    @ApiOperation(value = "Phương thức lấy danh sách tất cả các group của người dùng.")
    @GetMapping("/list")
    @Secured({"ROLE_USER"})
    public List<GroupDomain> findUserGroups(@ApiParam(hidden = true) Authentication auth) {
        UserInfo userdto = (UserInfo) auth.getPrincipal();

        List<GroupDomain> groups = groupsService.findAllByUser(userdto.getUserId());
        return groups;
    }

    @ApiOperation(value = "Phương thức lấy thông tin của người dùng.")
    @GetMapping("/findByUser/{username}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN"})
    public List<GroupDomain> findGroupsByUsername(@RequestParam("username") String username,
                                                  @ApiParam(hidden = true) Authentication auth) {
        List<GroupDomain> groups = groupsService.getGroupsByUsername(username);
        return groups;
    }

//	@DeleteMapping("/{groupid}/{email}")
//	@ResponseStatus(HttpStatus.OK)
//	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
//	public Boolean findGroupsByEmail(@RequestParam("groupid") String groupId, @RequestParam("email") String email,
//			Authentication auth) {
//		// Remove people out of Group.
//		// Need to check permission.
//		return true;
//	}

    @ApiOperation(value = "Phương thức thêm người dùng vào trong nhóm.")
    @PostMapping("/{groupid}/{userid}")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public boolean addUserToGroups(@RequestParam("groupid") String groupId,
                                   @RequestParam("userid") String userId, @ApiParam(hidden = true) Authentication auth) throws Exception {
        UserInfo userdto = (UserInfo) auth.getPrincipal();
        // Add people out of Group.
        groupsService.addMemberToGroup(groupId, userId, userdto.getUserId());
        return true;
    }

    @ApiOperation(value = "Phương thức cập nhật thông tin cho Group.")
    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public GroupDomain updateGroupInfo(@PathVariable("groupid") String groupId,
                                       @RequestBody GroupDomain domain, @ApiParam(hidden = true) Authentication auth) throws Exception {

        UserInfo userdto = (UserInfo) auth.getPrincipal();
        // Update group information.
        domain = groupsService.updateGroupInformation(domain, userdto.getUserId());
        return domain;
    }

    @ApiOperation(value = "Phương thức lấy danh sách tất cả kênh con trong Group.")
    @GetMapping("/{groupid}")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public GroupInfoDomain getChannelByGroup(@PathVariable("groupid") String groupId,
                                             @ApiParam(hidden = true) Authentication auth) throws Exception {
        UserInfo userdto = (UserInfo) auth.getPrincipal();
        GroupInfoDomain groupDomain = groupsService.getChannelByGroup(groupId, userdto.getUserId());
        return groupDomain;
    }

    @ApiOperation(value = "Phương thức tạo ra Group tạm thời trong trường hợp người dùng chưa đăng nhập nhưng lại muốn tạo ra Group.")
    @PostMapping("/temp-group")
    public GroupDomain createTempGroup(@RequestBody GroupDomain domain) throws Exception {
        GroupDomain group = groupsService.addTempGroup(domain);
        return group;
    }
}