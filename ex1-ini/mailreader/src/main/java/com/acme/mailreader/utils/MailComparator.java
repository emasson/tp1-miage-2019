package com.acme.mailreader.utils;

import java.util.Comparator;

import com.acme.mailreader.model.Mail;

/**
 * Comparateur de mails
 * 
 * Comme on désire afficher les mails les plus importants en premier, l'element le plus grand retourne une valeur négative
 *
 */
public class MailComparator implements Comparator<Mail> {
	
	public static final int MAILS_EQUALS = 0;
	public static final int FIRST_SMALLER = 1;
	public static final int FIRST_BIGGER = -1;
	
	public int compare(Mail mail1, Mail mail2) {
		if (unMailEstNull(mail1, mail2))
			return MAILS_EQUALS;
		if (importanceDifferents(mail1, mail2)) {
			return triParImportances(mail1, mail2);
		}
		if (statutsDifferents(mail1, mail2)) {
			return triParStatuts(mail1, mail2);
		}
		if (sujetsDifferents(mail1, mail2))
			return triParSujets(mail1, mail2);
		return mail2.getDate().compareTo(mail1.getDate());
	}
	
	private Boolean unMailEstNull(Mail mail1, Mail mail2) {
		return mail1 == null || mail2 == null;
	}
	
	private Boolean importanceDifferents(Mail mail1, Mail mail2) {
		return mail1.isImportant() != mail2.isImportant();
	}
	
	private Boolean statutsDifferents(Mail mail1, Mail mail2) {
		return mail1.getStatut() != mail2.getStatut();
	}
	
	private Boolean sujetsDifferents(Mail mail1, Mail mail2) {
		return mail1.getSujet() != mail2.getSujet();
	}
	
	private int triParImportances(Mail mail1, Mail mail2) {
		if (mail1.isImportant() && !mail2.isImportant())
			return FIRST_BIGGER;
		else
			return FIRST_SMALLER;
	}
	
	private int triParStatuts(Mail mail1, Mail mail2) {
		int comp = mail1.getStatut().ordinal()
				- mail2.getStatut().ordinal();
		return comp > 0 ? FIRST_BIGGER : FIRST_SMALLER;
	}
	
	private int triParSujets(Mail mail1, Mail mail2) {
		return mail2.getSujet().compareTo(mail1.getSujet());
	}
	
}
