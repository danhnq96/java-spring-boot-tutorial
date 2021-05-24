/**
 *
 */
package com.csf.whoami.template;

/**
 * @author mba0019
 *
 */
public class TemplateController {
//	@GetMapping(value = {"/contacts/add"})
//	public String showAddContact(Model model) {
//	    Contact contact = new Contact();
//	    model.addAttribute("add", true);
//	    model.addAttribute("contact", contact);
//	 
//	    return "contact-edit";
//	}
//	 
//	@PostMapping(value = "/contacts/add")
//	public String addContact(Model model,
//	        @ModelAttribute("contact") Contact contact) {        
//	    try {
//	        Contact newContact = contactService.save(contact);
//	        return "redirect:/contacts/" + String.valueOf(newContact.getId());
//	    } catch (Exception ex) {
//	        // log exception first, 
//	        // then show error
//	        String errorMessage = ex.getMessage();
//	        logger.error(errorMessage);
//	        model.addAttribute("errorMessage", errorMessage);
//	 
//	        //model.addAttribute("contact", contact);
//	        model.addAttribute("add", true);
//	        return "contact-edit";
//	    }        
//	}
//	 
//	@GetMapping(value = {"/contacts/{contactId}/edit"})
//	public String showEditContact(Model model, @PathVariable long contactId) {
//	    Contact contact = null;
//	    try {
//	        contact = contactService.findById(contactId);
//	    } catch (ResourceNotFoundException ex) {
//	        model.addAttribute("errorMessage", "Contact not found");
//	    }
//	    model.addAttribute("add", false);
//	    model.addAttribute("contact", contact);
//	    return "contact-edit";
//	}
//	 
//	@PostMapping(value = {"/contacts/{contactId}/edit"})
//	public String updateContact(Model model,
//	        @PathVariable long contactId,
//	        @ModelAttribute("contact") Contact contact) {        
//	    try {
//	        contact.setId(contactId);
//	        contactService.update(contact);
//	        return "redirect:/contacts/" + String.valueOf(contact.getId());
//	    } catch (Exception ex) {
//	        // log exception first, 
//	        // then show error
//	        String errorMessage = ex.getMessage();
//	        logger.error(errorMessage);
//	        model.addAttribute("errorMessage", errorMessage);
//	 
//	         model.addAttribute("add", false);
//	        return "contact-edit";
//	    }
//	}

//	@RequestMapping(value = "/management/deliveryedit", method = RequestMethod.GET)
//  public String filterDelivery(@Param(value = "odrNo") String odrNo,
//                               @Param(value = "fjpCtlNo") String fjpCtlNo,
//                               @Param(value = "codrNoH") String codrNoH,
//                               @Param(value = "grpKbn") String grpKbn,
//                               @Param(value = "dlvPrtCorpCode") String dlvPrtCorpCode,
//                               @Param(value = "dlvPrtStorCode") String dlvPrtStorCode, Model model)
//          throws OrderNotFoundException {
//      // Managerセッションを取得する
//      MasDirector masDirector = (MasDirector) session.getAttribute("managerSession");
//      // Get total row
//      int totalRow = deliveryEditService.getTotalRow(odrNo, fjpCtlNo, codrNoH, grpKbn, dlvPrtCorpCode, dlvPrtStorCode);
//      if (totalRow < 1) {// Check exits data
//          throw new OrderNotFoundException("オーダNo = " + odrNo + " , FJP管理No = " + fjpCtlNo);
//      }
//      // Get parameter when the first load page.
//      model.addAttribute("odrNo", odrNo);
//      model.addAttribute("fjpCtlNo", fjpCtlNo);
//      model.addAttribute("codrNoH", codrNoH);
//      model.addAttribute("grpKbn", grpKbn);
//      model.addAttribute("dlvPrtCorpCode", dlvPrtCorpCode);
//      model.addAttribute("dlvPrtStorCode", dlvPrtStorCode);
//      // メニュー画面にリダイレクトをする
//      model.addAttribute("manager", masDirector);
//
//      // Get info reply email
//      LogUser infoReplyEmail = deliveryEditService.getInfoReplyEmail(odrNo, fjpCtlNo, codrNoH);
//
//      if (infoReplyEmail == null) {
//          model.addAttribute("replyEmail", "未回答");
//      } else {
//
//          // LuanND check convert datatime display
//          if (!ValidateData.checkEmpty(infoReplyEmail.getDateSend())) {
//              try {
//                  infoReplyEmail.setDateSend(ConvertData.StringToDateString(infoReplyEmail.getDateSend()));
//              } catch (ParseException e) {
//                  e.printStackTrace();
//              }
//          }
//          model.addAttribute("replyEmail",
//                  "回答済  ユーザID: " + infoReplyEmail.getUserId() + " "
//                          + infoReplyEmail.getUserName() + " 処理時間  "
//                          + infoReplyEmail.getDateSend());
//      }
//      return "deliveryedit";
//  }

//	@RequestMapping(value = "/management/updateDelivery", method = RequestMethod.POST, produces = "application/json")
//  @ResponseBody
//  public String responseUpdateDelivery(@RequestBody final String listUpdateDelivery)
//          throws Exception, SQLException {
//      JSONObject result = new JSONObject();
//      int resultUpdate = updateDelivery(listUpdateDelivery);
//      if (resultUpdate < 0) {        // Check max-length
//          // Set message validate max-length error
//          result.put("status", "manRplDlvDatYmd" + resultUpdate * -1 + "");
//          result.put("message", MessageFormat.format(env.getProperty("COM_E015"), "[顧客提示内容]", "20"));
//          return result.toString();
//      }
//      if (resultUpdate > 0) {        // Update successfully
//          // Set message update success
//          result.put("status", "success");
//          result.put("message", env.getProperty("COM_I005"));
//      } else {
//          // Set message update error
//          result.put("status", "error");
//          result.put("message", env.getProperty("COM_E033"));
//      }
//      return result.toString();
//  }

//	@RequestMapping(value = "/management/search-delivery", produces = "application/json", method = RequestMethod.GET)
//  @ResponseBody
//  public ResponseObject getListAll(String companyCode, String shopCode,
//                                   String fjpNo, String orderNo, String orderNoH, String orderNoB,
//                                   String userOdrS, String userOdrE, String odrNbrYmdS,
//                                   String odrNbrYmdE, String deliveryS, String deliveryE,
//                                   String validDeadLineS, String validDeadLineE,
//                                   String createDateStart, String createDateEnd, boolean flagPlan,
//                                   boolean flagAllocate, boolean flagBills, boolean flagShipped,
//                                   boolean flagSendMail, boolean flagNotSendMail, int page,
//                                   int itemsPerPage) throws ParseException, JsonProcessingException,
//          SQLException {
//
//      // check valid data
//      if (page < 1 // page number not valid
//              || itemsPerPage < 1 // items per page not valid
//              || ValidateData.checkEmpty(companyCode)) {// company code is null or empty
//          return null;
//      }
//
//      // check max length data input
//      if (ValidateData.checkMaxLength(companyCode, 10)
//              || ValidateData.checkMaxLength(shopCode, 6)
//              || ValidateData.checkMaxLength(fjpNo, 15)
//              || ValidateData.checkMaxLength(orderNo, 14)
//              || ValidateData.checkMaxLength(orderNoH, 15)
//              || ValidateData.checkMaxLength(orderNoB, 15)
//              || ValidateData.checkMaxLength(userOdrS, 10)
//              || ValidateData.checkMaxLength(userOdrE, 10)
//              || ValidateData.checkMaxLength(odrNbrYmdS, 10)
//              || ValidateData.checkMaxLength(odrNbrYmdE, 10)
//              || ValidateData.checkMaxLength(deliveryS, 10)
//              || ValidateData.checkMaxLength(deliveryE, 10)
//              || ValidateData.checkMaxLength(validDeadLineS, 10)
//              || ValidateData.checkMaxLength(validDeadLineE, 10)
//              || ValidateData.checkMaxLength(createDateStart, 10)
//              || ValidateData.checkMaxLength(createDateEnd, 10)) {
//          return null;
//      }
//
//		/* Convert data time */
//      // ユーザ注文日
//      int userOdrSConvert = ConvertData.ConvertDatime(userOdrS);
//      // ユーザ注文日
//      int userOdrEConvert = ConvertData.ConvertDatime(userOdrE);
//      // FJ発行日
//      int odrNbrYmdSConvert = ConvertData.ConvertDatime(odrNbrYmdS);
//      // FJ発行日
//      int odrNbrYmdEConvert = ConvertData.ConvertDatime(odrNbrYmdE);
//      // 納期
//      int deliverySConvert = ConvertData.ConvertDatime(deliveryS);
//      // 納期
//      int deliveryEConvert = ConvertData.ConvertDatime(deliveryE);
//      // 有効期限
//      int validDeadLineSConvert = ConvertData.ConvertDatime(validDeadLineS);
//      // 有効期限
//      int validDeadLineEConvert = ConvertData.ConvertDatime(validDeadLineE);
//      // 更新日
//      int createDateSConvert = ConvertData.ConvertDatime(createDateStart);
//      // 更新日
//      int createDateEConvert = ConvertData.ConvertDatime(createDateEnd);
//
//      // check valid datetime
//      if (userOdrSConvert == -1 || userOdrEConvert == -1
//              || odrNbrYmdSConvert == -1 || odrNbrYmdEConvert == -1
//              || deliverySConvert == -1 || deliveryEConvert == -1
//              || validDeadLineSConvert == -1 || validDeadLineEConvert == -1
//              || createDateSConvert == -1 || createDateEConvert == -1) {
//          // datetime not valid
//          return null;
//      }
//
//		/* Check valid date start and date end */
//      if (!ValidateData.isValidDateStartEndInput(userOdrSConvert,
//              userOdrEConvert) // ユーザ注文日
//              || !ValidateData.isValidDateStartEndInput(odrNbrYmdSConvert,
//              odrNbrYmdEConvert) // 主オーダ発行年月日
//              || !ValidateData.isValidDateStartEndInput(deliverySConvert,
//              deliveryEConvert) // 得意先回答納期
//              || !ValidateData.isValidDateStartEndInput(validDeadLineSConvert,
//              validDeadLineEConvert) // 有効期限
//              || !ValidateData.isValidDateStartEndInput(createDateSConvert,
//              createDateEConvert)) { // データ作成年月日
//          return null;
//      }
//
//      return deliveryManagementService.getResponeObject(companyCode,
//              shopCode, fjpNo, orderNo, orderNoH, orderNoB, userOdrSConvert,
//              userOdrEConvert, odrNbrYmdSConvert, odrNbrYmdEConvert,
//              deliverySConvert, deliveryEConvert, validDeadLineSConvert,
//              validDeadLineEConvert, createDateSConvert, createDateEConvert,
//              flagPlan, flagAllocate, flagBills, flagShipped, flagSendMail,
//              flagNotSendMail, page, itemsPerPage);
//
//  }


    // TODO: Template call RestTemplate without param and return list object
//	private List<TypeInfoDomain> initialTypeInfoDomain() {
//		List<TypeInfoDomain> domains = new ArrayList<>();
//		
//		StringBuilder internalUrl = new StringBuilder();
//		internalUrl.append("http://localhost:8080");
//		internalUrl.append("/api/mw/type-info");
//		String url = internalUrl.toString();
//
//
//		try {
//			ResponseEntity<List<TypeInfoDomain>> claimResponse = restTemplate.exchange(
//					url, 
//					HttpMethod.GET,
//					null,
//					new ParameterizedTypeReference<List<TypeInfoDomain>>() {});
//			if(claimResponse != null && claimResponse.hasBody()){
//				domains = claimResponse.getBody();
//			}
//		} catch (RestClientException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return domains;
//	}

    // TODO: Stackoverflow
//	ResponseEntity<List<Rate>> rateResponse =
//	        restTemplate.exchange("https://bitpay.com/api/rates",
//	                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Rate>>() {
//	            });
//	List<Rate> rates = rateResponse.getBody();
}
