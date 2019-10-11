package com.pvn.mvctiles.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pvn.mvctiles.model.Issue;
import com.pvn.mvctiles.model.IssueComment;
import com.pvn.mvctiles.model.UserDetails;
import com.pvn.mvctiles.service.IssueService;
import com.pvn.mvctiles.service.ProjectService;
import com.pvn.mvctiles.service.UserService;
import com.pvn.mvctiles.vo.CommentsWrapper;
import com.pvn.mvctiles.vo.IssueCommentVO;
import com.pvn.mvctiles.vo.IssueVO;

@Controller
public class IssueController
{
	@Autowired
	private IssueService issueService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/app/user/issue-list")
	public String getAllIssues(Model model, HttpServletRequest request)
	{
		List<IssueVO> list = issueService.listIssue();
		model.addAttribute("issues", list);
		
		return "issue-list";
	}
	
	@RequestMapping("/app/user/issue-details")
	public String preAdd(Model model, HttpServletRequest request)
	{
		IssueVO issue = new IssueVO();
		model.addAttribute("issue", issue);
		model.addAttribute("users", userService.listUser());
		model.addAttribute("projects", projectService.listProject());

		return "issue-details";
	}
	
	@RequestMapping("/app/user/issue-details/{issueId}")
	public String preEdit(@PathVariable("issueId") int issueId, Model model, HttpServletRequest request)
	{
		IssueVO issue = issueService.getIssue(issueId);
		List<UserDetails> users = userService.listUser();
		List<IssueCommentVO> commentVOs = issueService.getCommentVOs(issueId, users);
		
		CommentsWrapper wrapper = new CommentsWrapper();
		wrapper.setCommentList(commentVOs);
		
		IssueComment issueComment = new IssueComment();
		issueComment.setIssueId(issueId);
		
		String username= ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		users.forEach(user -> {
			if(user.getUserName().equals(username))
			{
				issueComment.setUserId(user.getId());
			}
		});
		
		model.addAttribute("issueComment", issueComment);
		model.addAttribute("issue", issue);
		model.addAttribute("users", userService.listUser());
		model.addAttribute("projects", projectService.listProject());
		model.addAttribute("commentsWrapper", wrapper);
		model.addAttribute("update", true);
		
		return "issue-details";
	}
	
	@RequestMapping("/app/user/issue-list/{projectId}")
	public String getAllIssuesInProject(@PathVariable("projectId") int projectId, Model model, HttpServletRequest request)
	{
		List<IssueVO> list = issueService.listIssue(projectId);
		model.addAttribute("issues", list);
		
		return "issue-list";
	}
	
	@RequestMapping("/app/user/add-issue")
	public String addIssue(@ModelAttribute("issue") Issue issue, Model model, HttpServletRequest request)
	{
		issueService.addIssue(issue);
		List<IssueVO> list = issueService.listIssue(issue.getProjectId());
		model.addAttribute("issues", list);
		
		return "issue-list";
	}
	
	@RequestMapping("/app/user/modify-issue")
	public String modifyIssue(@ModelAttribute("issue") Issue issue, Model model, HttpServletRequest request)
	{
		issueService.modifyIssue(issue);
		List<IssueVO> list = issueService.listIssue(issue.getProjectId());
		model.addAttribute("issues", list);
		
		return "issue-list";
	}
	
	@RequestMapping("/app/user/issuecomment/delete/{issueId}/{commentId}")
	public String deleteIssueComment(@PathVariable("issueId") int issueId, 
							  @PathVariable("commentId") int commentId)
	{
		issueService.deleteIssueComment(commentId);
		return "redirect:/app/user/issue-details/"+issueId;
	}
	
	@RequestMapping("/app/user/issue/addComment")
	public String addIssueComment(@ModelAttribute("issueComment") IssueComment issueComment)
	{
		issueService.addIssueComment(issueComment);
		return "redirect:/app/user/issue-details/"+issueComment.getIssueId();
	}
}
