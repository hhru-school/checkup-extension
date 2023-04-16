// @ts-nocheck
import { initReactI18next } from "react-i18next";
import HttpApi from "i18next-http-backend";
import LanguageDetector from "i18next-browser-languagedetector";
import i18next from "i18next";

i18next
  .use(HttpApi)
  .use(initReactI18next)
  .use(LanguageDetector)
  .init({
    fallbackLng: "ru",
    load: "currentOnly",
    keySeparator: false,
    whitelist: ["ru", "en"],
  });

export default i18next;
