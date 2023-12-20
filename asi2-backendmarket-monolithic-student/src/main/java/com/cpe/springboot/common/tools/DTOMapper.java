package com.cpe.springboot.common.tools;
import com.cpe.springboot.card.model.CardDTO;
import com.cpe.springboot.card.model.CardModel;
import com.cpe.springboot.conversation.model.ConversationDTO;
import com.cpe.springboot.conversation.model.ConversationModel;
import com.cpe.springboot.message.model.MessageDTO;
import com.cpe.springboot.message.model.MessageModel;
import com.model.UserDTO;
import com.cpe.springboot.user.model.UserModel;

public class DTOMapper {
	
	public static CardDTO fromCardModelToCardDTO(CardModel cM) {
		CardDTO cDto =new CardDTO(cM);
		return cDto;
	}
	
	public static CardModel fromCardDtoToCardModel(CardDTO cD) {
		CardModel cm=new CardModel(cD);
		cm.setEnergy(cD.getEnergy());
		cm.setHp(cD.getHp());
		cm.setDefence(cD.getDefence());
		cm.setAttack(cD.getAttack());
		cm.setPrice(cD.getPrice());
		cm.setId(cD.getId());
		return cm;
	}
	
	
	public static UserDTO fromUserModelToUserDTO(UserModel uM) {
		UserDTO uDto =new UserDTO();
		uDto.setId(uM.getId());
		uDto.setLogin(uM.getLogin());
		uDto.setPwd(uM.getPwd());
		uDto.setAccount(uM.getAccount());
		uDto.setLastName(uM.getLastName());
		uDto.setSurName(uM.getSurName());
		uDto.setEmail(uM.getEmail());
		for (CardModel card : uM.getCardList()) {
			uDto.getCardList().add(card.getId());
		}
		return uDto;
	}
	
	public static MessageDTO fromMessageModelToMessageDTO(MessageModel mM) {
		MessageDTO mDto =new MessageDTO(mM);
		return mDto;
	}
	
	public static ConversationDTO fromConversationModelToConversationDTO(ConversationModel cM) {
		ConversationDTO cDto =new ConversationDTO(cM);
		return cDto;
	}
	
}
