import { UserShowDto } from "./userShowDto";

export interface ShowAction {
  label: string;
  visible?: (show: UserShowDto) => boolean;
  execute: (show: UserShowDto) => void;
}