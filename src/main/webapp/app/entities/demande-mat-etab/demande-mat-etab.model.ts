import dayjs from 'dayjs/esm';
import { IEtablissement } from 'app/entities/etablissement/etablissement.model';
import { NomDep } from 'app/entities/enumerations/nom-dep.model';
import { TypeEtab } from 'app/entities/enumerations/type-etab.model';
import { StatutEtab } from 'app/entities/enumerations/statut-etab.model';
import { TypeInspection } from 'app/entities/enumerations/type-inspection.model';

export interface IDemandeMatEtab {
  id?: number;
  nomComplet?: string;
  responsabilite?: string;
  nomEtab?: string;
  departement?: NomDep;
  typeEtab?: TypeEtab;
  statut?: StatutEtab;
  anneeDemande?: dayjs.Dayjs;
  typeInsp?: TypeInspection;
  etatDemande?: string | null;
  email?: string;
  objet?: string | null;
  etablissement?: IEtablissement | null;
}

export class DemandeMatEtab implements IDemandeMatEtab {
  constructor(
    public id?: number,
    public nomComplet?: string,
    public responsabilite?: string,
    public nomEtab?: string,
    public departement?: NomDep,
    public typeEtab?: TypeEtab,
    public statut?: StatutEtab,
    public anneeDemande?: dayjs.Dayjs,
    public typeInsp?: TypeInspection,
    public etatDemande?: string | null,
    public email?: string,
    public objet?: string | null,
    public etablissement?: IEtablissement | null
  ) {}
}

export function getDemandeMatEtabIdentifier(demandeMatEtab: IDemandeMatEtab): number | undefined {
  return demandeMatEtab.id;
}
