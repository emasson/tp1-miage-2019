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
	
	public int compare(Mail obj1, Mail obj2) {
		if (obj1 == null || obj2 == null) {
			return MAILS_EQUALS;
		}
		if (obj1.isImportant() != obj2.isImportant()) {
			if (obj1.isImportant() && !obj2.isImportant()) {
				return FIRST_BIGGER;
			} else {
				return FIRST_SMALLER;
			}
		}
		if (obj1.getStatut() != obj2.getStatut()) {
			int comp = obj1.getStatut().ordinal()
					- obj2.getStatut().ordinal();
			return comp > 0 ? FIRST_BIGGER : FIRST_SMALLER;
		}
		if (obj1.getSujet() != obj2.getSujet()) {
			return obj2.getSujet().compareTo(obj1.getSujet());
		}
		return obj2.getDate().compareTo(obj1.getDate());
	}
	
}
