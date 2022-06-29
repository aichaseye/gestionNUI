import dayjs from 'dayjs/esm';
import { IApprenant } from 'app/entities/apprenant/apprenant.model';
import { Sexe } from 'app/entities/enumerations/sexe.model';

export interface IDemandeMatApp {
  id?: number;
  nomCompletApp?: string;
  email?: string;
  telephone?: string | null;
  dateNaissance?: dayjs.Dayjs;
  adresse?: string | null;
  etatDemande?: string | null;
  sexe?: Sexe;
  nationalite?: string;
  numeroInscription?: number;
  objet?: string | null;
  matriculeApp?: string | null;
  anneeDemande?: dayjs.Dayjs;
  apprenant?: IApprenant | null;
}

export class DemandeMatApp implements IDemandeMatApp {
  constructor(
    public id?: number,
    public nomCompletApp?: string,
    public email?: string,
    public telephone?: string | null,
    public dateNaissance?: dayjs.Dayjs,
    public adresse?: string | null,
    public etatDemande?: string | null,
    public sexe?: Sexe,
    public nationalite?: string,
    public numeroInscription?: number,
    public objet?: string | null,
    public matriculeApp?: string | null,
    public anneeDemande?: dayjs.Dayjs,
    public apprenant?: IApprenant | null
  ) {}
}

export function getDemandeMatAppIdentifier(demandeMatApp: IDemandeMatApp): number | undefined {
  return demandeMatApp.id;
}
