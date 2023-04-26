/**
 * @task Отцентровать див
 * @description Задача на умение верстать
 * @link https://miro.com/app/board/uXjVMXuK3qc=/?moveToWidget=3458764550553845206&cot=14
 * @jest-environment puppeteer
 */
import "expect-puppeteer";
import { describe, expect, it, beforeAll } from "@jest/globals";
import { configureToMatchImageSnapshot } from "jest-image-snapshot";

const toMatchImageSnapshot = configureToMatchImageSnapshot({
  customSnapshotsDir: `${__dirname}/__snapshots__`,
  customDiffDir: `${__dirname}/__diff__`,
});

expect.extend({ toMatchImageSnapshot });

describe("Решение", () => {
  beforeAll(async () => {
    await page.goto(`file://${__dirname}/solution/index.html`, {
      waitUntil: "load",
    });
    await page.setViewport({ width: 200, height: 120 });
  });

  it("совпадает с примером", async () => {
    const image = await page.screenshot();

    expect(image).toMatchImageSnapshot({
      failureThreshold: 0.0,
      failureThresholdType: "percent",
    });
  });
});
