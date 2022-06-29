import dayjs from 'dayjs/esm';
import { IEtablissement } from 'app/entities/etablissement/etablissement.model';
import { IApprenant } from 'app/entities/apprenant/apprenant.model';
import { NomDiplome } from 'app/entities/enumerations/nom-diplome.model';

export interface IDiplome {
  id?: number;
  nom?: NomDiplome;
  matriculeDiplome?: string | null;
  anneeObtention?: dayjs.Dayjs | null;
  etablissement?: IEtablissement | null;
  apprenant?: IApprenant | null;
}

export class Diplome implements IDiplome {
  constructor(
    public id?: number,
    public nom?: NomDiplome,
    public matriculeDiplome?: string | null,
    public anneeObtention?: dayjs.Dayjs | null,
    public etablissement?: IEtablissement | null,
    public apprenant?: IApprenant | null
  ) {}
}

export function getDiplomeIdentifier(diplome: IDiplome): number | undefined {
  return diplome.id;
}
