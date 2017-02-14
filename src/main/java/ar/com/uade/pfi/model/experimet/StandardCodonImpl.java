package ar.com.uade.pfi.model.experimet;

import ar.com.uade.pfi.dao.dbo.entities.CodonEntity;
import ar.com.uade.pfi.model.Codon;
import ar.com.uade.pfi.model.CodonAbstract;

public class StandardCodonImpl extends CodonAbstract implements Codon {
	public StandardCodonImpl() {}
	
	public StandardCodonImpl(CodonEntity codon, double porcGC) {
		super(codon, porcGC);
	}
}
