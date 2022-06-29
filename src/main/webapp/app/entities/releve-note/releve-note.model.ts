import dayjs from 'dayjs/esm';
import { IApprenant } from 'app/entities/apprenant/apprenant.model';
import { IFiliereEtude } from 'app/entities/filiere-etude/filiere-etude.model';
import { ISerieEtude } from 'app/entities/serie-etude/serie-etude.model';
import { IProgramme } from 'app/entities/programme/programme.model';
import { Etat } from 'app/entities/enumerations/etat.model';
import { NomSemestre } from 'app/entities/enumerations/nom-semestre.model';

export interface IReleveNote {
  id?: number;
  annee?: dayjs.Dayjs;
  etat?: Etat | null;
  apreciation?: string;
  moyenne?: number | null;
  moyenneGenerale?: number | null;
  rang?: string | null;
  noteConduite?: number | null;
  nomSemestre?: NomSemestre | null;
  matriculeRel?: string | null;
  apprenant?: IApprenant | null;
  filiereEtude?: IFiliereEtude | null;
  serieEtude?: ISerieEtude | null;
  programmes?: IProgramme[] | null;
}

export class ReleveNote implements IReleveNote {
  constructor(
    public id?: number,
    public annee?: dayjs.Dayjs,
    public etat?: Etat | null,
    public apreciation?: string,
    public moyenne?: number | null,
    public moyenneGenerale?: number | null,
    public rang?: string | null,
    public noteConduite?: number | null,
    public nomSemestre?: NomSemestre | null,
    public matriculeRel?: string | null,
    public apprenant?: IApprenant | null,
    public filiereEtude?: IFiliereEtude | null,
    public serieEtude?: ISerieEtude | null,
    public programmes?: IProgramme[] | null
  ) {}
}

export function getReleveNoteIdentifier(releveNote: IReleveNote): number | undefined {
  return releveNote.id;
}
