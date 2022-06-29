import dayjs from 'dayjs/esm';
import { IInscription } from 'app/entities/inscription/inscription.model';
import { IReleveNote } from 'app/entities/releve-note/releve-note.model';
import { IDiplome } from 'app/entities/diplome/diplome.model';
import { INiveauEtude } from 'app/entities/niveau-etude/niveau-etude.model';
import { Sexe } from 'app/entities/enumerations/sexe.model';
import { Etat } from 'app/entities/enumerations/etat.model';

export interface IApprenant {
  id?: number;
  nomCompletApp?: string;
  email?: string;
  telephone?: string | null;
  dateNaissance?: dayjs.Dayjs;
  photoContentType?: string | null;
  photo?: string | null;
  adresse?: string | null;
  matriculeApp?: string | null;
  sexe?: Sexe;
  etat?: Etat | null;
  nationalite?: string;
  inscriptions?: IInscription[] | null;
  releveNotes?: IReleveNote[] | null;
  diplomes?: IDiplome[] | null;
  niveauEtude?: INiveauEtude | null;
}

export class Apprenant implements IApprenant {
  constructor(
    public id?: number,
    public nomCompletApp?: string,
    public email?: string,
    public telephone?: string | null,
    public dateNaissance?: dayjs.Dayjs,
    public photoContentType?: string | null,
    public photo?: string | null,
    public adresse?: string | null,
    public matriculeApp?: string | null,
    public sexe?: Sexe,
    public etat?: Etat | null,
    public nationalite?: string,
    public inscriptions?: IInscription[] | null,
    public releveNotes?: IReleveNote[] | null,
    public diplomes?: IDiplome[] | null,
    public niveauEtude?: INiveauEtude | null
  ) {}
}

export function getApprenantIdentifier(apprenant: IApprenant): number | undefined {
  return apprenant.id;
}
