/**
 * @task Шаблонизатор
 * @description Задача на знание DOM API
 * @jest-environment jsdom
 */
import { describe, expect, it } from "@jest/globals";
import userEvent from "@testing-library/user-event";

const setup = (markup = "") => {
  document.body.innerHTML = markup;

  // ⬇️ Решение ⬇️
  function solution(entryPoint) {
    if (!(entryPoint instanceof Element)) {
      return;
    }

    const operations = [
      {
        name: "copy",
        run(element, count) {
          for (let i = 0; i < count; i++) {
            let newElement = element.cloneNode(true);
            element.after(newElement);
            element = newElement;
          }
        },
      },
      {
        name: "remove",
        run(element, count) {
          for (
            let i = 0;
            i < count && Boolean(element.nextElementSibling);
            i++
          ) {
            element.nextElementSibling.remove();
          }
        },
      },
      {
        name: "removeChildren",
        run(element, count) {
          for (let i = 0; i < count && element.children.length > 0; i++) {
            element.children[0].remove();
          }
        },
      },
      {
        name: "switch",
        run(element, count) {
          const parent = element.parentElement;
          const currentElementIndex = Array.prototype.indexOf.call(
            parent.children,
            element
          );
          const elementToSwap =
            parent.children[
              (currentElementIndex + count) % parent.children.length
            ];

          const sibling =
            element.nextSibling === elementToSwap
              ? element
              : element.nextSibling;

          parent.insertBefore(element, elementToSwap);
          parent.insertBefore(elementToSwap, sibling);
        },
      },
    ];

    for (const { name, run } of operations) {
      for (const element of entryPoint.querySelectorAll(
        `:scope > [x-make^="${name}:"]`
      )) {
        const command = element.getAttribute("x-make") || "";
        const arg = parseInt(command.substring(name.length + 1), 10);

        element.removeAttribute("x-make");

        if (isNaN(arg)) {
          return;
        }

        run(element, arg);
      }
    }

    for (const child of entryPoint.children) {
      solution(child);
    }
  }
  // ⬆️ Решение ⬆️

  return {
    user: userEvent.setup(),
    solution,
    node: document.body.firstElementChild,
  };
};

describe("Решение", () => {
  it("должно существовать и быть функцией", () => {
    const { solution } = setup();

    expect(solution).toBeDefined();
    expect(solution).toEqual(expect.any(Function));
  });

  it("должно отрабатывать, когда работа отсутствует", () => {
    const { solution, node } = setup(`<entry></entry>`);

    expect(() => solution(node)).not.toThrow();
    expect(node).toMatchSnapshot();
  });

  it("должно отрабатывать - 1", async () => {
    const { solution, node } = setup(`
      <entry>
        <div>
          <div x-make="remove:1">Блок 1</div>
          <div x-make="copy:3">Блок 2</div>
        </div>
      </entry>
    `);

    expect(() => solution(node)).not.toThrow();
    expect(node).toMatchSnapshot();
  });
});
